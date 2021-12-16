package com.acme;

public class FakeDetailsFacade extends DetailsFacade{

    @Override
    public int availableProcessors() {
        return 4;
    }

    @Override
    public long freeMemory() {
        return 127268272;
    }

    @Override
    public String jreVersion() {
        return "15.0.2+7-27";
    }

    @Override
    public String tempLocation() {
        return "M:\\AppData\\Local\\Temp";
    }

    @Override
    public long totalMemory() {
        return 159383552;
    }
}
