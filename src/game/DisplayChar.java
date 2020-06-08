package game;

public enum DisplayChar {
    FARMER('F'),
    CORSPE('c'),
    CROP('!'),
    DIRT('.'),
    FENCE('#'),
    FOOD('o'),
    HUMAN('H'),
    MAMBOMARIE('M'),
    PLANK(')'),
    PLAYER('@'),
    RIPECROP('$'),
    SHOTGUN('S'),
    SHOTGUNAMMO('s'),
    SNIPER('X'),
    SNIPERAMMO('x'),
    TREE('+'),
    YOUNGTREE('t'),
    GROWNTREE('T'),
    VEHICLE('V'),
    ZOMBIE('Z'),
    ZOMBIEARM('1'),
    ZOMBIELEG('7'),
    ZOMBIECLUB('|'),
    ZOMBIEMAZE('[')
    ;

    private final Character displayChar;

    private DisplayChar(final Character ch) {
        this.displayChar = ch;
    }

    public char toChar() {
    	char ch = displayChar;
        return ch;
    }
}
