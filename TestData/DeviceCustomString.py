import random

# Define possible segments
simple_segments = ["nan", "Personal_email", "Standard"]
complex_segments = ["MSSQLSERVER", "Super_Administrator**General", "Super_Administrator**Default"]

def generate_device_custom_string():
    # Randomly choose whether to use a simple or complex segment
    if random.choice([True, False]):
        segment = random.choice(simple_segments)
    else:
        segment = random.choice(complex_segments)
    
    return segment

# Generate a few sample device custom strings
for _ in range(5):
    print(generate_device_custom_string())
