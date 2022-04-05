package main

import (
	"bufio"
	"os"
	"strconv"
	"strings"
)

func main() {
	var input =
		"12\n" +
		"5"

	s := bufio.NewScanner(strings.NewReader(input))
	w := bufio.NewWriter(os.Stdout)

	s.Scan()
	x, _ := strconv.Atoi(s.Text())
	s.Scan()
	y, _ := strconv.Atoi(s.Text())

	if 0 < x && 0 < y {
		_, _ = w.WriteString("1")
	} else if x < 0 && 0 < y {
		_, _ = w.WriteString("2")
	} else if x < 0 && y < 0 {
		_, _ = w.WriteString("3")
	} else {
		_, _ = w.WriteString("4")
	}

	_ = w.Flush()
}