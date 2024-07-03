import random

def generate_random_devicehostname(count):
    devicehostnames = ['CHN_PRIMARY_FW', 'HIS_MUM_FW_Primary', 'MCAFEE_HANDLER', 
                       'HIS_lab_Fw_1', 'isupport.MICROCLINIC.IN', 'chkmgmt-hisysmc', 'MCAFEEEPO',
                       'microlog-fw01', 'fw01', 'fw02']  # Adjust as needed
    
    random_devicehostnames = random.choices(devicehostnames, k=count)
    formatted_string = '";"'.join(random_devicehostnames)
    formatted_string = f'"{formatted_string}"'
    
    return formatted_string

# Example usage
if __name__ == "__main__":
    count = 10
    devicehostnames = generate_random_devicehostname(count)
    print("Generated random devicehostnames:")
    print(devicehostnames)
