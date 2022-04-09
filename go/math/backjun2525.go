package main

import (
	"bufio"
	"os"
	"strconv"
	"strings"
)

func main() {
	input :=
		"14 30\n" +
		"20"

	s := bufio.NewScanner(strings.NewReader(input))
	w := bufio.NewWriter(os.Stdout)

	s.Scan()
	sArray := strings.Split(s.Text(), " ")
	h, _ := strconv.Atoi(sArray[0])
	m, _ := strconv.Atoi(sArray[1])
	s.Scan()
	c, _ := strconv.Atoi(s.Text())

	m += c
	h = ((m / 60) + h) % 24
	m = m % 60

	_, _ = w.WriteString(strconv.Itoa(h))
	_, _ = w.WriteString(" ")
	_, _ = w.WriteString(strconv.Itoa(m))
	_ = w.Flush()
}