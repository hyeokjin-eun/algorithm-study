// let input = require('fs').readFileSync('/dev/stdin').toString().split('\n');
let input =
    '7\n' +
    '35'.split('\n');

let n;
let target;
let array;
let xa = [0, 1, 0, -1];
let ya = [1, 0, -1, 0];
let x;
let y;

function solution() {
    setData();
    setAnswer();
    print();
}

function setData() {
    n = stoi(input[0]);
    target = stoi(input[2]);
    array = new Array(n);
    for (let i = 0; i < n; i++) {
        array[i] = new Array(n).fill(0);
    }
}

function stoi(s) {
    return parseInt(s);
}

function setAnswer() {
    let count = n * n;
    let start = {x: 0, y:0};
    let weight = 0;
    while (count !== 0) {
        let xs = start.x + weight;
        let xe = n - weight - 1;
        let ys = start.y + weight;
        let ye = n - weight - 1;

        // left
        for (let i = ys; i < ye; i++) {
            if (array[i][xs] === 0) {
                array[i][xs] = count--;
                if (target === array[i][xs]) {
                    x = xs;
                    y = i;
                }
            }
        }

        // bottom
        for (let i = xs; i < xe; i++) {
            if (array[ye][i] === 0) {
                array[ye][i] = count--;
                if (target === array[ye][i]) {
                    x = i;
                    y = ye;
                }
            }
        }

        // right
        for (let i = ye; i >= 0; i--) {
            if (array[i][xe] === 0) {
                array[i][xe] = count--;
                if (target === array[i][xe]) {
                    x = xe;
                    y = i;
                }
            }
        }

        // top
        for (let i = xe; i >= 0; i--) {
            if (array[ys][i] === 0) {
                array[ys][i] = count--;
                if (target === array[ys][i]) {
                    x = i;
                    y = ys;
                }
            }
        }

        weight++;
    }
}

function print() {
    let string = '';
    for (let i = 0; i < n; i++) {
        for (let j = 0; j < n; j++){
            string += array[i][j];
            if (j !== n - 1) {
                string += ' ';
            }
        }


        if (i !== n - 1) {
            string += '\n';
        }
    }

    string += '\n';
    string += (y + 1) + ' ' + (x + 1);
    console.log(string);
}

solution();