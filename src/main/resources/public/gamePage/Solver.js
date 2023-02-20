
alert("Press on the board to set it to starting state of the game. Then Press Enter twice to solve. (it might take a second or 2 to solve)");

let game;
let gameOptions = {
    rows: 6,
    cols: 6,
    tileSize: 100,
}

let level = [
    [0, 0, 0, 0, 0, 0],
    [0, 0, 0, 0, 0, 0],
    [0, 0, 0, 0, 0, 0],
    [0, 0, 0, 0, 0, 0],
    [0, 0, 0, 0, 0, 0],
    [0, 0, 0, 0, 0, 0]
]
// function start() {
//     let gameConfig = {
//         type: Phaser.AUTO,
//         backgroundColor: 0x444444,
//         scale: {
//             mode: Phaser.Scale.FIT,
//             autoCenter: Phaser.Scale.CENTER_BOTH,
//             parent: "theSolver",
//             width: 600,
//             height: 600
//         },
//         scene: playGame
//     }
//     game = new Phaser.Game(gameConfig);
//
// }

window.onload = function start() {
    let gameConfig = {
        type: Phaser.AUTO,
        backgroundColor: 0x444444,
        scale: {
            mode: Phaser.Scale.FIT,
            autoCenter: Phaser.Scale.CENTER_BOTH,
            parent: "theSolver",
            width: 600,
            height: 600
        },
        scene: playGame
    }
    game = new Phaser.Game(gameConfig);

}

class playGame extends Phaser.Scene {
    enterKey;
    constructor() {
        super("PlayGame");
    }
    preload(){
        this.load.spritesheet("tiles", "tiles.png", {
            // frameHeight: gameOptions.tileSize,
            frameWidth: gameOptions.tileSize
            });
    }
    create() {
        this.enterKey = this.input.keyboard.addKey(Phaser.Input.Keyboard.KeyCodes.ENTER);
        this.gameArray = [];
        for (let i = 0; i < gameOptions.rows; i++) {
            this.gameArray[i] = [];
            for (let j = 0; j < gameOptions.cols; j++) {
                let tile = this.add.sprite(j * 100, i * 100, "tiles", level[i][j]);
                tile.setOrigin(0);
                this.gameArray[i][j] = {
                    value: level[i][j],
                    sprite: tile
                }
            }
        }
        console.log(this.gameArray)
        this.input.on("pointerdown", this.changeTitle, this);
        // this.gameText = this.add.text(620, 20, "", {
        //     fontFamily: "Arial",
        //     fontSize: 24
        // });

    }

    update () {
        // If Enter is pressed
        if (Phaser.Input.Keyboard.JustDown(this.enterKey)) {
            sendBoard();
            for (let i = 0; i < gameOptions.rows; i++) {
                for (let j = 0; j < gameOptions.cols; j++) {
                    if (this.gameArray[i]!== undefined && this.gameArray[i][j] !== undefined) {
                        this.gameArray[i][j].value = level[i][j];
                        this.gameArray[i][j].sprite.setFrame(2 * this.gameArray[i][j].value + this.gameArray[i][j].value % 2);
                    }
                }
            }
            console.log(this.gameArray);
        }

    }
    changeTitle(pointer) {
        let row = Math.floor(pointer.y / gameOptions.tileSize);
        let col = Math.floor(pointer.x / gameOptions.tileSize);
        if (this.gameArray[row] !== undefined && this.gameArray[row][col] !== undefined) {
            this.gameArray[row][col].value = Phaser.Math.Wrap(this.gameArray[row][col].value + 1, 0, 3);
            this.gameArray[row][col].sprite.setFrame(2 * this.gameArray[row][col].value + this.gameArray[row][col].value % 2);
            this.changeLevel();
        }
    }

     changeLevel() {
         for (let i = 0; i < gameOptions.rows; i++) {
             for (let j = 0; j < gameOptions.cols; j++) {
                 level[i][j] = this.gameArray[i][j].value;
             }
         }
        console.log(level);
    }


}


function sendBoard() {
    console.log(arrayToString(level));
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/api/v1/board/sendInput",
        data: arrayToString(level),
        contentType: "application/json; charset=utf-8",
        // dataType: "json",
        processData: false,
        success: function (response) {
            console.log(response);


            // playGame.scene.updateTiles(resultToArr(response));
            level = resultToArr(response);
            console.log(level);
            // console.log(playGame.prototype.gameArray);
            // playGame.prototype.updateTiles();
        },
        error: function (error) {
            console.log("Error! Message:", error);
            alert("Invalid Board!");
        }
    })

    // playGame.prototype.update();

}
const arrayToString = (level)  =>{
    let str = '';
    for (let i = 0; i < gameOptions.rows; i++) {
        for (let j = 0; j < gameOptions.cols; j++) {
            if (level[i][j] === 0) {
                str += 'x';
            }
            if (level[i][j] === 1) {
                str += '0';
            }
            if (level[i][j] === 2) {
                str += '1';
            }
        }
        if (i < gameOptions.rows - 1) {
            str += '/';
        }
    }
    return str;
};

const resultToArr = (response) => {
    let arr = [];
    for (let i = 0; i < gameOptions.rows; i++) {
        arr[i] = [];
        for (let j = 0; j < gameOptions.cols; j++) {
            if (response[i][j] === '0') {
                arr[i][j] = 1;
            }
            if (response[i][j] === '1') {
                arr[i][j] = 2;
            }
        }
    }
    return arr;
}

// module.exports = resultToArr(response);