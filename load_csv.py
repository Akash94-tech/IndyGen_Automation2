# load_csv.py
# Author: Deepak Kundapura
# Date: 04/June/2024
# Description: This scripts iterates thru each csv file in the specified directory and  
# publishes to RedPanda topic.
# It can either publish JSON, or in CSV format along with headers

import csv
import sys
import os
import json
import time
from kafka import KafkaProducer

FILE_PATH = "./data/to_process/"
PROCESSED_PATH = "./data/processed/"
TOPIC = "publish-log"

def publish_csv(csvf):
    headers = csvf.readline()
    for l in csvf.readlines():
        message = bytes(json.dumps({
            'source': 'securonix_csv',
            'value': l,
            'headers': headers,
        }), 'utf-8')
        publish_message(message)

def publish_json(csvf):
    dicts = csv.DictReader(csvf)
    for d in dicts:
        message = bytes(json.dumps({
            'source': 'securonix',
            'value': d,
        }), 'utf-8')
        publish_message(message)

def publish_message(message):
    producer.send(TOPIC, message)

def move_file_to_processed(filename):
    # Move the file to processed folder
    os.rename(f"{FILE_PATH}{filename}", f"{PROCESSED_PATH}{filename}")

def process_file(filename, is_json):
    try: 
        with open(f'{FILE_PATH}{filename}', encoding='utf-8-sig') as csvf:
            if is_json:
                publish_json(csvf)
            else:
                publish_csv(csvf)
        move_file_to_processed(filename)
    except Exception as e:
        print(f"Error processing {filename}: {e}")
    
if __name__ == "__main__":
    
    # Read sys.args
    # Check if --json
    # If so publish JSON else CSV with headers
    producer = KafkaProducer(bootstrap_servers='localhost:19092')

    argvs = sys.argv
    is_json = len(argvs) > 1 and argvs[1] == "--json"
    # Get list of files in data directory 
    files = os.listdir(FILE_PATH)
    start = time.time()
    # Process each file in the data directory
    for filename in files:
        process_file(filename, is_json)
    producer.flush()
    producer.close()
    end = time.time()
    print(f'Took {end-start}s.')
