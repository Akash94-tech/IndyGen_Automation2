import random
import string

def generate_device_direction():
    # Generate a random device direction with exactly 7 alphabetical characters
    direction = ''.join(random.choices(string.ascii_letters, k=7))
    return direction

# Generate a few sample device directions
for _ in range(5):
    print(generate_device_direction())
