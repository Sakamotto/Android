package com.testes.agenda.entidade;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * Created by cristian on 03/06/16.
 */
public class AppendingObjectOutputStream extends ObjectOutputStream {

    public AppendingObjectOutputStream(OutputStream output) throws IOException {
        super(output);
    }

    @Override
    public void writeStreamHeader() throws IOException {
        reset();
    }
}
