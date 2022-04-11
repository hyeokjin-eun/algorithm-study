package main

import (
	"bufio"
	"os"
	"strconv"
	"strings"
)

func main() {
	input :=
		"6 2 5"

	s := bufio.NewScanner(strings.NewReader(input))
	w := bufio.NewWriter(os.Stdout)

	s.Scan()
	strs := strings.Split(s.Text(), " ")

	temp1, _ := strconv.Atoi(strs[0])
	temp2, _ := strconv.Atoi(strs[1])
	temp3, _ := strconv.Atoi(strs[2])

	price := 0
	if temp1 == temp2 && temp2 == temp3 {
		price = 10000 + temp1 * 1000
	} else if temp1 == temp2 || temp2 == temp3 || temp1 == temp3 {
		if temp1 == temp2 {
			price = 1000 + temp1 * 100
		} else if temp2 == temp3 {
			price = 1000 + temp2 * 100
		} else {
			price = 1000 + temp1*100
		}
	} else {
		if temp1 > temp2 && temp1 > temp3 {
			price = temp1 * 100
		} else if temp2 > temp1 && temp2 > temp3 {
			price = temp2 * 100
		} else if temp3 > temp1 && temp3 > temp2 {
			price = temp3 * 100
		}
	}

	_, _ = w.WriteString(strconv.Itoa(price))
	_ = w.Flush()
}