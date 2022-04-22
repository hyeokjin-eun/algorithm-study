package main

import (
	"bufio"
	"os"
	"strconv"
	"strings"
)

func main() {
	input :=
		"6\n" +
			"3 4 2 12 6 8"

	r := bufio.NewScanner(strings.NewReader(input))
	w := bufio.NewWriter(os.Stdout)

	r.Scan()
	num, _ := strconv.Atoi(r.Text())

	r.Scan()
	ss := strings.Split(r.Text(), " ")

	max := 0
	min := 10000001
	for i := 0; i < num; i++ {
		temp, _ := strconv.Atoi(ss[i])
		if max < temp {
			max = temp
		}

		if temp < min {
			min = temp
		}
	}

	answer := max * min
	_, _ = w.WriteString(strconv.Itoa(answer))
	_ = w.Flush()
}
