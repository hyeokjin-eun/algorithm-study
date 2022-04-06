// link
// https://www.acmicpc.net/problem/2605

package main

import (
	"bufio"
	"os"
	"strconv"
	"strings"
)

func main() {
	var input =
		"5\n" +
		"0 1 1 3 2"

	s := bufio.NewScanner(strings.NewReader(input))
	w := bufio.NewWriter(os.Stdout)

	s.Scan()
	num,_ := strconv.Atoi(s.Text())
	s.Scan()
	selection := strings.Split(s.Text(), " ")
	answer := make([]int, 0, num)
	for i := range selection {
		index := i + 1
		temp, _ := strconv.Atoi(selection[i])
		copy(answer[index - temp : index], answer[index - temp - 1:])
		answer = answer[:index]
		answer[index - temp - 1] = index
	}

	for i := range answer {
		_, _ = w.WriteString(strconv.Itoa(answer[i]))
		if i != len(answer) - 1 {
			_, _ = w.WriteString(" ")
		}
	}

	_ = w.Flush()
}
