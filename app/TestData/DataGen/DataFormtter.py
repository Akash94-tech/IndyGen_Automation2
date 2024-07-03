start_end_char  ='"'
sep = '","'
account     = 'UNKNOWN'
ipaddress = '162.243.147.25'
deviceaction = 'Accept'

csvRowRecord = start_end_char + account + sep + ipaddress + sep+deviceaction+start_end_char

print(csvRowRecord)
