# List available recipes
default:
	@just --list

# Clean build and install the library
install:
	mvn clean install
