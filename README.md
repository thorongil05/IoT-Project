Docs is in the [wiki](https://github.com/thorongil05/IoT-Project/wiki)

[![CodeFactor](https://www.codefactor.io/repository/github/thorongil05/flagms/badge)](https://www.codefactor.io/repository/github/thorongil05/flagms)

# Instructions

To run this application, you need to:

1. open three terminal windows
2. on the first terminal, write the folling commands to run cooja:
    1. "contikier"
    2. cd "tools/cooja"
    3. ant run
3. on the contiki application, load the file "simulation.csc" (or "final_simulation.csc" if you want 2 flags and 2 track limits)
3. on the second terminal, run the script "start-border-router.sh" in the project root to start the border router
4. on the third terminal, run the script "start.sh" in the project root to start the CLI application
5. start the simulation on cooja
