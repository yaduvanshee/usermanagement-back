#!/bin/bash

# Default values
DEFAULT_HOST="localhost"
DEFAULT_PORT="5432"
SQL_FILE="create_database_schema.sql"

# Parse command-line parameters
HOST=${1:-$DEFAULT_HOST}
PORT=${2:-$DEFAULT_PORT}

if ! psql -h "$HOST" -p "$PORT" -U core_api_user -lqt | cut -d \| -f 1 | grep -qw user_management; then
    if createdb -h "$HOST" -p "$PORT" -U core_api_user -E UTF8 -O core_api_user user_management; then
        echo "Database 'user_management' created successfully"
    else
        echo "Failed to create database 'user_management'"
        exit 1
    fi
else
    echo "Database 'user_management' already exists"
fi

if psql -h "$HOST" -p "$PORT" -U core_api_user -d user_management -f "$SQL_FILE"; then
    echo "Tables created successfully"
else
    echo "Failed to create tables"
    exit 1
fi


