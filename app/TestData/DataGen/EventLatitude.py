import uuid

def generate_custom_uuid():
    # Generate a UUID
    generated_uuid = uuid.uuid4()
    # Convert UUID to a string and remove dashes
    uuid_str = str(generated_uuid).replace("-", "")
    # Format the UUID according to the specific pattern
    custom_uuid = '-'.join([uuid_str[:8], uuid_str[8:12], uuid_str[12:16], uuid_str[16:20], uuid_str[20:]])
    return custom_uuid

# Generate a custom UUID in the specified format
print(generate_custom_uuid())
