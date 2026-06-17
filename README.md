# kata-rover-mission

## Build rover.jar

From the project root, run:

```powershell
mvn clean install
```

This generates `rover.jar` at the project root with `rovermission.RoverMissionApplication` as the entry point.

## Run

```powershell
java -jar rover.jar input.txt
```

The application reads and prints the content of the input file when an argument is provided.