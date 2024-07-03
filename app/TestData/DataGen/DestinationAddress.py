from faker import Faker

# Initialize Faker
fake = Faker()

def generate_ip_address():
    # Generate a random IPv4 address
    ip_address = fake.ipv4()
    return ip_address

# Generate a few sample IP addresses
for _ in range(5):
    print(generate_ip_address())