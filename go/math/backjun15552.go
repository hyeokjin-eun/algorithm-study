package main

import (
	"bufio"
	"os"
	"strconv"
	"strings"
)

func main() {
	input :=
		"5\n" +
		"1 1\n" +
		"12 34\n" +
		"5 500\n" +
		"40 60\n" +
		"1000 1000"

	s := bufio.NewScanner(strings.NewReader(input))
	w := bufio.NewWriter(os.Stdout)

	s.Scan()
	num, _ := strconv.Atoi(s.Text())

	for i := 0; i < num; i++ {
		s.Scan()
		str := strings.Split(s.Text(), " ")
		A, _ := strconv.Atoi(str[0])
		B, _ := strconv.Atoi(str[1])
		_, _ = w.WriteString(strconv.Itoa(A + B))
		if i != num - 1 {
			_, _ = w.WriteString("\n")
		}
	}

	_ = w.Flush()
}