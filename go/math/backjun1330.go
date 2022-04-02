// link
// https://www.acmicpc.net/problem/1330

package main

import (
	"bufio"
	"os"
	"strconv"
	"strings"
)

var input = "1 2"

func main() {
	r := bufio.NewReader(strings.NewReader(input))
	w := bufio.NewWriter(os.Stdout)

	str, _ := r.ReadString('\n')
	str = strings.TrimSpace(str)
	strArray := strings.Split(str, " ")

	a, _ := strconv.Atoi(strArray[0])
	b, _ := strconv.Atoi(strArray[1])
	if a < b {
		w.WriteString("<")
	} else if a > b {
		w.WriteString(">")
	} else {
		w.WriteString("==")
	}

	w.Flush()
}
