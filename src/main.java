import java.util.ArrayList;
private static ArrayList<String> list = new ArrayList<>();
private static boolean programRunning = true;
private static boolean lastWasPrint = false;

void main() {
    Scanner in = new Scanner(System.in);
    do {
        IO.println();
        if (!lastWasPrint) PrintList(false); // do not print if print was last menu function
        String option = SafeInput.getRegExString(in,
                "Menu option? [A]dd [D]elete [I]nsert [P]rint [Q]uit: ",
                "^(?i)a|d|i|p|q$",
                "A/D/I/P/Q");

        switch (option.toLowerCase()) {
            case "a" -> AddList(in);
            case "d" -> DeleteList(in);
            case "i" -> InsertList(in);
            case "p" -> PrintList(true);
            case "q" -> QuitProgram(in);
        }
    } while (programRunning);
}

private static void AddList(Scanner in) {
    String addStr = SafeInput.getNonZeroLenString(in, "What would you like to add? ");
    list.add(addStr);
    IO.println("Added " + addStr);
}

private static void DeleteList(Scanner in) {
    int location = SafeInput.getRangedInt(in, "What list item # would you like to delete? ",
            0, list.size());
    if (list.get(location) == null || list.get(location).isBlank()) {
        IO.println("Index [" + location + "] is already empty");
    } else {
        list.remove(location);
        IO.println("Deleted [" + location + "]");
    }
}

private static void InsertList(Scanner in) {
    String str = SafeInput.getNonZeroLenString(in, "What would you like to insert? ");
    int location = SafeInput.getRangedInt(in,
            "Where # would you like to insert it? ",
            0,
            list.size());

    list.add(location, str);
    System.out.printf("Inserted %s at [%d]\n", str, location);
}

private static void PrintList(boolean called) {
    // called refers to if the function was called from a menu option (true) or from the loop (false)
    lastWasPrint = called;

    if (called && !list.isEmpty()) IO.println("---------------------------");
    for (int i = 0; i < list.size(); i++) {
        System.out.printf("[%d] %s\n", i, list.get(i));
    }
    if (called && !list.isEmpty()) IO.println("---------------------------");
}

private static void QuitProgram(Scanner in) {
    programRunning = !SafeInput.getYNConfirm(in, "Are you sure you want to quit? [y/n]: ");
}