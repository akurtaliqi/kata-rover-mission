# kata-rover-mission

A Java implementation of the [Mars Rover kata](https://kata-log.rocks/mars-rover-kata).

NASA deploys robotic rovers on a rectangular plateau on Mars. Each rover is given an initial position (x, y, direction) and a sequence of commands (`L`, `R`, `M`) to navigate the grid. The application processes an input file containing the plateau size and rover instructions, then outputs the final position and heading of each rover.

## Build

```powershell
mvn clean install
```

Produces `rover.jar` at the project root.

## Run

```powershell
java -jar rover.jar input.txt
```

### Input format

```
5 5
1 2 N
LMLMLMLMM
3 3 E
MMRMMRMRRM
```

### Expected output

```
1 3 N
5 1 E
```

## Design decisions

| # | Decision | Behaviour |
|---|----------|-----------|
| 1 | **Plateau boundary** | A move that would take a rover outside the plateau is **ignored** — the rover stays in place. No exception is thrown. However, an informative warning log is printed. This is a conservative default; the rule can be changed to allow boundary-crossing if the business requires it. |
| 2 | **Rover collisions** | Collisions are **ignored** — rovers are treated as independent agents and may occupy the same coordinates simultaneously. This assumption can be revisited if collision detection becomes a requirement. |
| 3 | **Input validation** | The input file is strictly validated before parsing (plateau format, rover position format, command characters, overall file structure). Any malformed input raises an explicit exception with a clear error message. |

