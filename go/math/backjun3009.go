package main

import (
	"bufio"
	"os"
	"strconv"
	"strings"
)

func main() {
	input :=
		"5 5\n" +
			"5 7\n" +
			"7 5"

	r := bufio.NewScanner(strings.NewReader(input))
	w := bufio.NewWriter(os.Stdout)

	r.Scan()
	point1 := strings.Split(r.Text(), " ")
	r.Scan()
	point2 := strings.Split(r.Text(), " ")
	r.Scan()
	point3 := strings.Split(r.Text(), " ")
	x1, _ := strconv.Atoi(point1[0])
	x2, _ := strconv.Atoi(point2[0])
	x3, _ := strconv.Atoi(point3[0])

	y1, _ := strconv.Atoi(point1[1])
	y2, _ := strconv.Atoi(point2[1])
	y3, _ := strconv.Atoi(point3[1])

	x := 0
	y := 0
	if x1 == x2 {
		x = x3
	} else if x1 == x3 {
		x = x2
	} else {
		x = x1
	}

	if y1 == y2 {
		y = y3
	} else if y1 == y3 {
		y = y2
	} else {
		y = y1
	}

	_, _ = w.WriteString(strconv.Itoa(x) + " " + strconv.Itoa(y))
	_ = w.Flush()
}
