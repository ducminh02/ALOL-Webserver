// var n = parseInt(window.prompt("Enter Board Size"));

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


window.onload = function () {
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
        console.log("test")
        this.input.on("pointerdown", this.changeTitle, this);
        this.gameText = this.add.text(620, 20, "", {
            fontFamily: "Arial",
            fontSize: 24
        });

    }
    changeTitle(pointer) {
        let row = Math.floor(pointer.y / gameOptions.tileSize);
        let col = Math.floor(pointer.x / gameOptions.tileSize);
        if (this.gameArray[row] !== undefined && this.gameArray[row][col] !== undefined) {
            this.gameArray[row][col].value = Phaser.Math.Wrap(this.gameArray[row][col].value + 1, 0, 3);
            this.gameArray[row][col].sprite.setFrame(2 * this.gameArray[row][col].value + this.gameArray[row][col].value % 2);
            this.changeLevel();
            // if (this.boardComplete()) {
            //
            // }
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
// function sendBoard() {
//     console.log(playGame.gameArray[0][0].value);
//     // let data = JSON.stringify(this.gameArray);
//     // let xhr = new XMLHttpRequest();
//     // xhr.open("POST", "http://localhost:3000/board");
//     // xhr.setRequestHeader("Content-Type", "application/json");
//     // xhr.send(data);
// }

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
        },
        error: function (error) {
            console.log("Error! Message:", error);
        }
    })
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