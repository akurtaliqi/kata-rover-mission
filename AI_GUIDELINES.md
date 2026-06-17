# Mars Rover Kata - AI Development Guidelines

## Objective

Implement the Mars Rover (read BUISNESS_SPEC.md) challenge using modern Java with a clean, maintainable, and testable architecture.

The goal is not only to solve the algorithm but to demonstrate:

* Clean Code principles
* SOLID thinking when relevant
* Strong separation of responsibilities
* Domain-driven modeling
* Robust input validation
* High testability

---

# Technical Constraints

## Java Version

Use Java 21.

Preferred language features:

* Records
* Switch expressions
* Enum methods
* Immutable objects whenever possible

Avoid unnecessary frameworks.

---

## Build Tool

Use Maven.

Allowed dependencies:

* JUnit 5

Avoid:

* Spring Boot
* Lombok
* MapStruct
* Apache Commons
* Any dependency that does not bring clear value

---

# Architecture

Follow a layered architecture.

```text
src/main/java

fr.company.rover

├── domain
│   ├── model
│   └── exception
│
├── application
│
├── infrastructure
│   ├── parser
│   └── validator
│
└── Main
```

---

# Domain Layer

The domain must not depend on infrastructure.

The domain should contain only business concepts.

## Direction

Represent cardinal directions using an enum.

Responsibilities:

* turnLeft()
* turnRight()

Example:

```java
Direction.NORTH.turnLeft();
```

Avoid utility classes for direction management.

---

## Position

Use a record.

```java
public record Position(int x, int y)
```

Responsibilities:

* represent coordinates
* compute a new position after movement

The Position object should not know anything about files or commands.

---

## Plateau

Responsibilities:

* store plateau boundaries
* validate whether a position is inside the plateau

Example:

```java
boolean contains(Position position)
```

---

## Rover

Responsibilities:

* represent rover state

A rover owns:

* a position
* a direction

Avoid putting orchestration logic inside Rover.

The rover should not parse commands.

The rover should not read files.

The rover should not execute missions.

---

## Command

Represent commands using an enum.

```java
LEFT
RIGHT
MOVE
```

Provide a factory method:

```java
Command from(char value)
```

All character-to-command conversion must happen here.

---

# Application Layer

Application services orchestrate the domain.

---

## Mission

Represents a rover and its commands.

Example:

```java
public record Mission(
    Rover rover,
    List<Command> commands
)
```

---

## RoverCommandExecutor

Responsibilities:

Execute one command on one rover.

Example:

```java
execute(
    Rover rover,
    Command command,
    Plateau plateau
)
```

All command dispatching logic should be centralized here.

Avoid multiple switch statements across the codebase.

---

## MissionRunner

Responsibilities:

Execute a full mission.

Example:

```java
run(
    Mission mission,
    Plateau plateau
)
```

This service iterates over commands and delegates execution.

---

# Infrastructure Layer

Infrastructure handles I/O.

---

## InputValidator

Responsibilities:

Validate raw input.

Examples:

* plateau line format
* rover position format
* command line format
* file structure

Validation should happen before parsing.

---

## InputFileParser

Responsibilities:

Convert file content into domain objects.

Should produce:

* Plateau
* List<Mission>

Avoid business logic here.

---

# Error Handling

Create explicit exceptions.

Examples:

```java
InvalidInputException
InvalidCommandException
InvalidPositionException
```

Never throw generic RuntimeException for business errors.

Error messages must be clear and actionable.

---

# Input Validation Rules

Validate:

## Plateau

Expected format:

```text
5 5
```

Both values must be positive integers.

---

## Rover Position

Expected format:

```text
1 2 N
```

Direction must be one of:

```text
N E S W
```

---

## Commands

Allowed commands:

```text
L
R
M
```

Reject any unknown command.

---

## File Structure

Expected:

```text
plateau

rover position
commands

rover position
commands
```

After the first line, the remaining number of lines must be even.

---

# Movement Rules

A rover can:

* rotate left
* rotate right
* move forward

Movement depends on current direction.

Example:

```text
NORTH -> y + 1
SOUTH -> y - 1
EAST  -> x + 1
WEST  -> x - 1
```

---

# Plateau Boundaries

A rover cannot leave the plateau.

When a move would produce an invalid position:

* ignore the move
* keep the rover at its current position

Do not crash the application.

Document this behavior.

---

# Collision Management

Ignore rover collisions.

Assumption:

Rovers are independent.

Multiple rovers may occupy the same coordinates.

Document this assumption.

---

# Testing Strategy

Create unit tests before implementing file parsing.

Minimum tests:

## Direction

* north -> left -> west
* north -> right -> east

## Position

* move north
* move south
* move east
* move west

## Plateau

* valid position
* invalid position

## RoverCommandExecutor

* execute left
* execute right
* execute move

## MissionRunner

Validate official sample scenario.

Input:

```text
1 2 N
LMLMLMLMM
```

Expected:

```text
1 3 N
```

---

# Coding Rules

## Prefer

* small classes
* explicit naming
* immutable objects
* records when appropriate

## Avoid

* God classes
* Utility classes
* Static business methods
* Business logic inside Main
* Business logic inside parsers
* Deep inheritance

---

# Main Class

Main should only:

1. Read arguments
2. Parse input file
3. Execute missions
4. Print results

Main must not contain business logic.

---

# Success Criteria

A reviewer should be able to:

* understand the architecture in under 2 minutes
* locate responsibilities immediately
* test the domain without file access
* extend the project without modifying existing business classes
