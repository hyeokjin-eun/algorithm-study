package main

func sum(a []int) int {
	sum := 0;
	for i := range a {
		sum += a[i]
	}

	return sum
}
