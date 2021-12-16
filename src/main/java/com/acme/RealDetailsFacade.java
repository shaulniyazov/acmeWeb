package com.acme;

public class RealDetailsFacade extends DetailsFacade {

    @Override
    public int availableProcessors() {
        return Runtime.getRuntime().availableProcessors();
    }

    @Override
    public long freeMemory() {
        return Runtime.getRuntime().freeMemory();
    }

    @Override
    public String jreVersion() {
        return "" + Runtime.version();
    }

    @Override
    public String tempLocation() {
        return System.getenv("TEMP");
    }

    @Override
    public long totalMemory() {
        return Runtime.getRuntime().totalMemory();
    }
}
