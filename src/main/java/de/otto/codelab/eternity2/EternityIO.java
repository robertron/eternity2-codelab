package de.otto.codelab.eternity2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.StringTokenizer;

public class EternityIO {

    public static void save(final File file, final Piece[][] pieces) {
        final String builder = stringifyBoard(pieces);

        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(file);
            stream.write(builder.toString().getBytes());
        }
        catch (final FileNotFoundException e) {
            System.err.println("Error saving eternity file!");
        }
        catch (final IOException e) {
            System.err.println("Error saving eternity file!");
        }
        finally {
            try {
                stream.close();
            }
            catch (final IOException e) {
                System.err.println("Couldn't close Stream!");
            }
        }

        System.out.println("File successfully saved to " + file.getAbsolutePath());
    }

    static String stringifyBoard(final Piece[][] pieces) {
        final StringBuilder builder = new StringBuilder();
        int i = 0;
        for (final Piece[] row : pieces) {
            int j = 0;
            for (final Piece piece : row) {
                builder.append(piece);
                if (row.length - 1 > j) {
                    builder.append("\t");
                }
                j++;
            }
            if (pieces.length - 1 > i) {
                builder.append("\n");
            }
            i++;
        }
        return builder.toString();
    }

    public static Piece[][] load(final File file, final int size) {
        LineNumberReader lnr = null;
        try {
            lnr = new LineNumberReader(new FileReader(file));
            return load(lnr, size);
        }
        catch (final IOException e) {
            System.err.println("Error opening eternity file!");
        }
        finally {
            try {
                lnr.close();
            }
            catch (final IOException e) {
                System.err.println("Couldn't close LineNumberReader!");
            }
        }
        return null;
    }

    static Piece[][] load(final BufferedReader reader, final int size) throws IOException {
        final Piece[][] map = new Piece[size][];
        String line = reader.readLine();

        int i = 0;
        while (line != null) {
            map[i] = parseLine(line, size);
            line = reader.readLine();
            i++;
        }
        return map;
    }

    static Piece[] parseLine(final String line, final int size) {
        final StringTokenizer stok = new StringTokenizer(line);
        final Piece[] pieces = new Piece[size];

        int i = 0;
        while (stok.hasMoreTokens()) {
            final Integer north = Integer.valueOf(stok.nextToken());
            final Integer east = Integer.valueOf(stok.nextToken());
            final Integer south = Integer.valueOf(stok.nextToken());
            final Integer west = Integer.valueOf(stok.nextToken());

            pieces[i] = Piece.from(north, east, south, west);
            i++;
        }
        return pieces;

    }

}
