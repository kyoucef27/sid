# Exercise 3: CORBA Distributed Address Book

## Description
A distributed address book system using CORBA that allows adding, removing, searching, and listing contacts remotely.

## Files
- `AddressBook.idl`: IDL definition for Contact struct, ContactList, and AddressBook interface
- `AddressBookPOAImpl.java`: Servant implementation
- `ServerPOA.java`: CORBA server
- `Client.java`: Interactive client for managing contacts

## Compilation Steps

### 1. Generate Java classes from IDL
```powershell
& "C:\Program Files\Java\jdk1.8.0_202\bin\idlj.exe" -fall AddressBook.idl
```

### 2. Compile all Java files
```powershell
& "C:\Program Files\Java\jdk1.8.0_202\bin\javac.exe" AddressBookApp\*.java *.java
```

## Running the Application

### 1. Start the naming service (in separate terminal)
```powershell
& "C:\Program Files\Java\jdk1.8.0_202\bin\orbd.exe" -ORBInitialPort 1050 -ORBInitialHost localhost
```

### 2. Start the server (in separate terminal)
```powershell
& "C:\Program Files\Java\jdk1.8.0_202\bin\java.exe" ServerPOA -ORBInitialPort 1050 -ORBInitialHost localhost
```

### 3. Run the client
```powershell
& "C:\Program Files\Java\jdk1.8.0_202\bin\java.exe" Client -ORBInitialPort 1050 -ORBInitialHost localhost
```

## Features
- Add new contacts with name, phone, and email
- Remove contacts by name
- Search for specific contacts
- List all contacts
- Exception handling for duplicate contacts and not found errors
