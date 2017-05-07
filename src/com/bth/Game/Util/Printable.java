package com.bth.Game.Util;


public abstract class Printable {
    public PrinterInterface printer = new Printer();

    public void setPrinter(PrinterInterface printer) {
        this.printer = printer;
    }
}
