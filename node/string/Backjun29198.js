// link : https://www.acmicpc.net/problem/29198

let submit = false;
let input = [];
function setInput() {
    if (submit === true) {
        input[0] = require('fs').readFileSync('/dev/stdin').toString().split('\n');
    } else {
        input[0] = '4 1 2\n' +
            'Y\n' +
            'Y\n' +
            'Z\n' +
            'X'
        input[1] = '3 4 2\n' +
            'ABCD\n' +
            'AACC\n' +
            'ABCC'
    }
}

setInput()

let N
let M
let K
let S = []
function solution() {
    input.forEach((item) => {
        setData(item)
        setAnswer()
        printAnswer()
    })
}

function setData(data) {
    let temp = data.split('\n')[0].split(' ')
    N = stoi(temp[0])
    M = stoi(temp[1])
    K = stoi(temp[2])
    S = []
    for (let i = 0; i < N; i++) {
        S[i] = data.split('\n')[i + 1]
    }
}

function stoi(s) {
    return parseInt(s)
}

let answer = []
let permutation = []
let temp = new Set()
function setAnswer() {
    answer = []
    permutation = []
    temp = new Set()
    permutation = Array.from({ length: N }, (_, i) => (i < K ? 1 : 0));
    do {
        let s = ''
        for (let i = 0; i < N; i++) {
            if (permutation[i] === 1) {
                s += S[i]
            }
        }

        temp.add([...s].sort().join(''))
    } while(prevPermutation())
    answer = [...temp]
    answer.sort()
}

function prevPermutation() {
    let i = permutation.length - 1
    while (i > 0 && permutation[i - 1] <= permutation[i]) {
        i--
    }

    if (i <= 0) {
        return false
    }

    let j = permutation.length - 1
    while (permutation[j] >= permutation[i - 1]) {
        j--
    }

    let temp = permutation[i - 1]
    permutation[i - 1] = permutation[j]
    permutation[j] = temp

    j = permutation.length - 1
    while (i < j) {
        temp = permutation[i]
        permutation[i] = permutation[j]
        permutation[j] = temp
        i++
        j--
    }

    return true
}

function printAnswer() {
    console.log(answer[0])
}

solution()