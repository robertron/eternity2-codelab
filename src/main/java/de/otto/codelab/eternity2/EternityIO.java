package de.otto.codelab.eternity2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.io.IOUtils;

import com.google.common.base.Joiner;

public class EternityIO {

    public static void save(final File file, final Piece[][] pieces) {
        final List<String> board = convert(pieces);

        try {
            IOUtils.writeLines(board, "\n", new FileOutputStream(file));
            System.out.println("File successfully saved to " + file.getAbsolutePath());
        }
        catch (final FileNotFoundException e) {
            System.err.println("Error saving eternity file!");
        }
        catch (final IOException e) {
            System.err.println("Error saving eternity file!");
        }
    }

    public static Piece[][] load(final File file, final int size) {
        try {
            final List<String> lines = IOUtils.readLines(new FileReader(file));
            return convert(lines, size);
        }
        catch (final FileNotFoundException e) {
            System.err.println("Error loading eternity file!");
        }
        catch (final IOException e) {
            System.err.println("Error loading eternity file!");
        }
        return null;
    }

    static List<String> convert(final Piece[][] pieces) {
        final List<String> result = new ArrayList<String>();
        for (final Piece[] row : pieces) {
            final String line = Joiner.on("\t").join(row);
            result.add(line);
        }
        return result;
    }

    static Piece[][] convert(final List<String> lines, final int size) {
        final Piece[][] map = new Piece[size][];
        int j = 0;
        for (final String line : lines) {
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
            map[j] = pieces;
            j++;
        }
        return map;
    }

}
