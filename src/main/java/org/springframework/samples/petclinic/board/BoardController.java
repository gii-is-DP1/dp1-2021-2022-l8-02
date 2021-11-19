package org.springframework.samples.petclinic.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/board")
public class BoardController {
    
    BoardService boardService;

    @Autowired
    public BoardController(BoardService board){
        this.boardService= board;
    }

    @PostMapping("/{boardId}/new")
        public void newBoard(@PathVariable("boarId") int id, Board boarModificate, Tile tile, int){
            Board board= boardService.finBoardById(id);
            if(tile.getTileState()==TileState.FREE){
                boardService.save(board);
            }
        }

    @PostMapping("/{boardId}")

}
