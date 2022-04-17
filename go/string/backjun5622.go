package main

import (
	"bufio"
	"os"
	"strconv"
	"strings"
)

func main() {
	input :=
		"UNUCIC"

	r := bufio.NewScanner(strings.NewReader(input))
	w := bufio.NewWriter(os.Stdout)

	r.Scan()
	chars := strings.Split(r.Text(), "")

	sum := 0
	for i := range chars {
		if chars[i] == "A" || chars[i] == "B" || chars[i] == "C" {
			sum += 2
		} else if chars[i] == "D" || chars[i] == "E" || chars[i] == "F" {
			sum += 3
		} else if chars[i] == "G" || chars[i] == "H" || chars[i] == "I" {
			sum += 4
		} else if chars[i] == "J" || chars[i] == "K" || chars[i] == "L" {
			sum += 5
		} else if chars[i] == "M" || chars[i] == "N" || chars[i] == "O" {
			sum += 6
		} else if chars[i] == "P" || chars[i] == "Q" || chars[i] == "R" || chars[i] == "S" {
			sum += 7
		} else if chars[i] == "T" || chars[i] == "U" || chars[i] == "V" {
			sum += 8
		} else {
			sum += 9
		}

		sum += 1
	}

	_, _ = w.WriteString(strconv.Itoa(sum))
	_ = w.Flush()
}
