package main

import (
	"bufio"
	"os"
	"strconv"
	"strings"
)

func main() {
	input :=
		"2100000000 9 10"

	r := bufio.NewScanner(strings.NewReader(input))
	w := bufio.NewWriter(os.Stdout)

	r.Scan()
	nums := strings.Split(r.Text(), " ")
	A, _ := strconv.Atoi(nums[0])
	B, _ := strconv.Atoi(nums[1])
	C, _ := strconv.Atoi(nums[2])

	if C <= B {
		_, _ = w.WriteString("-1")
	} else {
		_, _ = w.WriteString(strconv.Itoa((A / (C - B)) + 1))
	}

	_ = w.Flush()
}
