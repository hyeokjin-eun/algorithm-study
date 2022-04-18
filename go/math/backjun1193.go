package main

import (
	"bufio"
	"os"
	"strconv"
	"strings"
)

func main() {
	input :=
		"14"

	r := bufio.NewScanner(strings.NewReader(input))
	w := bufio.NewWriter(os.Stdout)

	r.Scan()
	num, _ := strconv.Atoi(r.Text())

	line := 0
	cnt := 0
	top := 0
	bottom := 0

	for cnt < num {
		line += 1
		cnt = line * (line + 1) / 2
	}

	if line%2 != 0 {
		top = 1 + cnt - num
		bottom = line + num - cnt
	} else {
		bottom = 1 + cnt - num
		top = line + num - cnt
	}

	_, _ = w.WriteString(strconv.Itoa(top) + "/" + strconv.Itoa(bottom))
	_ = w.Flush()
}
