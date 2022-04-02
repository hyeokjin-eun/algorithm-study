// link
// https://www.acmicpc.net/problem/1000

package main

import "fmt"

var a, b int
var answer int
func main() {
	setData()
	setAnswer()
	printAnswer()
}

func setData() {
	fmt.Scanf("%d %d", &a, &b)
}

func setAnswer() {
	answer = a + b
}

func printAnswer() {
	fmt.Println(answer)
}
