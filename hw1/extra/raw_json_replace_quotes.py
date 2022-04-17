
with open("./raw_json.txt", "r") as f:
    line = f.readline()
    line = line.replace("\"","\\\"")
    
    with open("./raw_json_out.txt", "w") as f2:
        f2.write(line)
        print("JSON Converted!")
