import random

def generate_destination_port():
    # Generate a random destination port between 0 and 65535
    port = random.randint(0, 655)
    return port

# Generate a few sample destination ports
for _ in range(5):
    print(generate_destination_port())
