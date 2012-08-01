package de.otto.codelab.eternity2;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.File;
import java.util.List;

import org.testng.annotations.Test;

public class EternityIOTest {

    @Test
    public void shouldRead2x2FileToPieces() {
        final File file = new File("data/2x2x4.txt");
        final Piece[][] result = EternityIO.load(file, 2);

        assertThat(result.length, is(2));
        assertThat(result[0].length, is(2));
        assertThat(result[1].length, is(2));

        assertCorrectPiece(result[0][0], 2, 0, 0, 1);
        assertCorrectPiece(result[0][1], 0, 2, 3, 0);
        assertCorrectPiece(result[1][0], 0, 0, 4, 1);
        assertCorrectPiece(result[1][1], 0, 0, 3, 4);
    }

    @Test
    public void shouldConvertBoardToString() {
        final Piece[][] pieces = new Piece[2][2];

        pieces[0] = new Piece[2];
        pieces[0][0] = Piece.from(1, 2, 3, 4);
        pieces[0][1] = Piece.from(4, 3, 2, 1);

        pieces[1] = new Piece[2];
        pieces[1][0] = Piece.from(0, 2, 3, 4);
        pieces[1][1] = Piece.from(4, 3, 2, 0);

        final List<String> board = EternityIO.convert(pieces);

        assertThat(board.get(0), is("1 2 3 4\t4 3 2 1"));
        assertThat(board.get(1), is("0 2 3 4\t4 3 2 0"));

    }

    private void assertCorrectPiece(final Piece piece, final int north, final int east, final int south, final int west) {
        assertThat(piece.getNorth(), is(north));
        assertThat(piece.getEast(), is(east));
        assertThat(piece.getSouth(), is(south));
        assertThat(piece.getWest(), is(west));
    }

}
