def bubbleSort(arr):
    n = len(arr)
 
    for i in range(n-1):
 
        for j in range(0, n-i-1):
 
            if arr[j] > arr[j + 1] :
                arr[j], arr[j + 1] = arr[j + 1], arr[j]
 



address = [0]
size = [32767]
while 1 :
	if address.__len__() == 1:
		print('Index  *  address  *  end  *  size')
		print('  {}        {}       {}     {}'.format(1,address[0],((address[0] + size[0]) - 1), size[0]))
	else:
		print('Index  *  address  *  end  *  size')
		for i in range(0,address.__len__()):
			print('  {}        {}       {}     {}'.format((i+1),address[i],((address[i] + size[i]) - 1), size[i]))

	choice = int(input('please choose your algorithm\n1)Best\n2)First\n'))

	if choice == 1:
		action = int(input('please make your decision\n1)Accept\n2)Assign\n'))
		if action == 1:
			tempaddr = int(input('enter addres : '))
			tempsize = int(input('enter size : '))
			address.append(tempaddr)
			size.append(tempsize)
		elif action == 2:
			temp = []
			addrindex = [];
			app = int(input('please enter app size : '))
			for i in range(0,address.__len__()):
				if app < size[i] or app == size[i]:
					temp.append(size[i])
					addrindex.append(i);
			if temp:
				if temp.__len__() == 1 and addrindex.__len__() == 1:
					print('SUCCESS!!! address is = {} '.format((temp[addrindex[0]] + address[addrindex[0]]) - app))
					size[addrindex[0]] = size[addrindex[0]] - app
				elif 1 < temp.__len__():
					bubbleSort(temp)
					for i in range(0, address.__len__()):
						if temp[0] == size[i]:
							index = i;
							break;

					print('SUCCESS!!! address is = {}'.format((size[index] + address[index]) - app))
					size[index] = size[index] - app
			else:
				print('\napp is too large!\n')
		else:
			print('\nunknown command!\n')

	elif choice == 2:
		action = int(input('please make your decision\n1)Accept\n2)Assign\n'))
		if action == 1:
			tempaddr = int(input('enter addres : '))
			tempsize = int(input('enter size : '))
			address.append(tempaddr)
			size.append(tempsize)
		
		elif action == 2:
			temp = []
			app = int(input('please enter the app size : '))
			for i in range(0,address.__len__()):
				if app < size[i] or app == size[i]:
					temp.append(size[i])
					index = i;
					break;

			print('SUCCESS!!! address is = {} '.format((address[index] + size[index])-app))
			size[index] = size[index] - app
			
			if temp:
				print()
			else:
				print('\napp is too large\n')
	else:
		print('wrong input!')