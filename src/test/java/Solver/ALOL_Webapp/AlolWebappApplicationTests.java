package Solver.ALOL_Webapp;

import Solver.ALOL_Webapp.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AlolWebappApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void TestStartingBoard() {
		BoardService service = new BoardService();
		char[][] board = new char[][]{{'1','0','x','1'},{'x','x','1','0'},{'1','x','0','x'}, {'0','0','1','1'}};

		assertEquals(board, service.startingBoard("10x1/xx10/1x0x/0011", 4));
	}

}
