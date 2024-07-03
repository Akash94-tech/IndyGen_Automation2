import random
import string

def generate_event_city():
    # Generate a random city name with exactly 3 alphabetical characters
    city_name = ''.join(random.choices(string.ascii_uppercase, k=3))
    return city_name

# Generate a few sample event city names
for _ in range(5):
    print(generate_event_city())
