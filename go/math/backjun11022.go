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
		"2 3\n" +
		"3 4\n" +
		"9 8\n" +
		"5 2"

	s := bufio.NewScanner(strings.NewReader(input))
	w := bufio.NewWriter(os.Stdout)

	s.Scan()
	test, _ := strconv.Atoi(s.Text())
	for i := 0; i < test; i++ {
		s.Scan()
		str := strings.Split(s.Text(), " ")
		A, _ := strconv.Atoi(str[0])
		B, _ := strconv.Atoi(str[1])
		_, _ = w.WriteString("Case #" + strconv.Itoa(1 + i) + ": ")
		_, _ = w.WriteString(strconv.Itoa(A) + " + " + strconv.Itoa(B) + " = ")
		_, _ = w.WriteString(strconv.Itoa(A + B))
		if i != test - 1 {
			_, _ = w.WriteString("\n")
		}
	}

	_ = w.Flush()
}