import sqlite3
from sqlite3 import Error

def create_connection(path):
    connection = None
    try:
        connection = sqlite3.connect(path)
        print("Connection to SQLite DB successful")
    except Error as e:
        print(f"The error '{e}' occurred")

    return connection

def execute_query(connection, query, fetch_result=False):
    cursor = connection.cursor()
    try:
        cursor.execute(query)
        connection.commit()
        if fetch_result:
            return cursor.fetchall()
    except sqlite3.Error as e:
        print(f"The error '{e}' occurred")
