## THE GAME

This game is played on a 5x5 square board with this starting position:

* Starting Position: <br />
| A B C D E <br />
--+---------- <br />
1 | O O O O X <br />
2 | O O O O O <br />
3 | O O X O O <br />
4 | O O O O O <br />
5 | X O O O O <br />


RULES
* TURNS - The Musketeers always go first. Each player makes one move before passing to their opponent.
* MUSKETEER - On the Musketeers’ turn, a musketeer can move to an adjacent orthogonal (neighboring,
non-diagonal) cell occupied by a guard, capturing it and occupying that cell.
* GUARD - On the Guards’ turn, a guard can move to an adjacent orthogonal empty cell.
* GOALS - <br />
– The Musketeers win if they cannot capture any guards AND if they are not on the same row or column. <br />
– The guards win if all the musketeers are on the same row or column at any point in the game.

AN EXAMPLE

The Guards’ turn: <br />
| A B C D E <br />
--+---------- <br />
1 | X <br />
2 | O <br />
3 | X O O <br />
4 | O <br />
5 | O O X <br />


– Guard moves D4 → E4 <br />
| A B C D E <br />
--+---------- <br />
1 | X <br />
2 | O <br />
3 | X O O <br />
4 | O <br />
5 | O O X <br />

– Musketeers’ only move is to capture the guard on C5 <br />
| A B C D E <br />
--+---------- <br />
1 | X <br />
2 | O <br />
3 | X O O <br />
4 | O <br />
5 | O X <br />

– Guard moves A5 → B5 <br />
| A B C D E <br />
--+---------- <br />
1 | X <br />
2 | O <br />
3 | X O O <br />
4 | O <br />
5 | O X <br />

– Musketeers can only capture the guard on B5 <br />
| A B C D E <br />
--+---------- <br />
1 | X <br />
2 | O <br />
3 | X O O <br />
4 | O <br />
5 | X <br />

– Guards win because 3 musketeers are all on the B column