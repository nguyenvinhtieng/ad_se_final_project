<form action="" method="POST">
    <div class="main-modal-create fixed w-full h-100 inset-0 z-50 overflow-hidden flex justify-center items-center animated fadeIn faster"
        style="background: rgba(0,0,0,.7);">
        <div
            class="border border-teal-500 shadow-lg modal-container bg-white w-11/12 md:max-w-md mx-auto rounded shadow-lg z-50 overflow-y-auto">
            <div class="modal-content py-4 text-left px-6">
                <!--Title-->
                <div class="flex justify-between items-center pb-3">
                    <p class="text-2xl font-bold">Add new School year</p>
                    <div class="modal-close-create cursor-pointer z-50">
                        <svg class="fill-current text-black" xmlns="http://www.w3.org/2000/svg" width="18" height="18"
                            viewBox="0 0 18 18">
                            <path
                                d="M14.53 4.53l-1.06-1.06L9 7.94 4.53 3.47 3.47 4.53 7.94 9l-4.47 4.47 1.06 1.06L9 10.06l4.47 4.47 1.06-1.06L10.06 9z">
                            </path>
                        </svg>
                    </div>
                </div>
                <!--Body-->
                <div class="my-5">
                    <div class="mb-4">
                        <label class="block text-gray-700 text-sm font-normal mb-2" for="username">
                            Name
                        </label>
                        <input
                            class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                            name="name" v-model="form.email" required autofocus />
                    </div>
                    <div class="mb-4">
                        <label class="block text-gray-700 text-sm font-normal mb-2" for="username">
                            Start day
                        </label>
                        <input type="date"
                            class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                            name="startday" v-model="form.email" required autofocus />
                    </div>
                    <div class="mb-4">
                        <label class="block text-gray-700 text-sm font-normal mb-2" for="username">
                            End day
                        </label>
                        <input type="date"
                            class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                            name="endday" v-model="form.email" required autofocus />
                    </div>

                </div>
                <!--Footer-->
                <div class="flex justify-end pt-2">
                    <button type="button"
                        class="focus:outline-none modal-close-create px-4 bg-gray-400 p-2 rounded-md text-black hover:bg-gray-300">Cancel</button>
                    <button
                        class="focus:outline-none px-4 bg-blue-500 p-2 ml-3 rounded-md text-white hover:bg-blue-400">Add</button>
                </div>
            </div>
        </div>
    </div>
</form>