import Navbar from "@/app/components/Navbar";
import Image from "next/image";

// TODO: REFACTOR

export default function Page() {
  return (
    <div className="bg-zinc-900 max-w-screen">
      <Navbar />

      <div className="flex max-w-7xl mx-auto mt-12">
        <div className="w-1/3 p-8">
          <div className="relative aspect-[3/4] flex justify-center items-center">
            <Image
              src="/thumbnail1.jpg"
              alt="thumbnail"
              layout="fill"
              objectFit="cover"
              objectPosition="center"
              priority
              className="rounded-2xl"
            />
          </div>
        </div>

        <div className="w-2/3 p-8 flex flex-col text-neutral-100">
          <div>
            <h1 className="font-oswald font-bold text-6xl">
              The Witcher - real story
            </h1>
            <p className="mt-4 text-lg">Director: Kamil Rudny</p>
          </div>

          <div className="mt-8 flex gap-x-4">
            <div className="bg-zinc-800 rounded-xl px-4 py-2 w-fit">
              Rating: 7.8
            </div>
            <div className="bg-zinc-800 rounded-xl px-4 py-2 w-fit">
              Duration: 2:15h
            </div>
            <div className="bg-zinc-800 rounded-xl px-4 py-2 w-fit">
              Year: 2020
            </div>
            <div className="bg-zinc-800 rounded-xl px-4 py-2 w-fit">
              Fantasy
            </div>
          </div>

          <div className="my-14">
            <p className="text-lg">
              Lorem Ipsum is simply dummy text of the printing and typesetting
              industry. Lorem Ipsum has been the industry's standard dummy text
              ever since the 1500s, when an unknown printer took a galley of
              type and scrambled it to make a type specimen book. It has
              survived not only five centuries, but also the leap into
              electronic typesetting, remaining essentially unchanged.It has
              survived not only five centuries, but also the leap into
              electronic typesetting, remaining essentially unchanged.It has
              survived not only five centuries, but also the leap into
              electronic typesetting, remaining essentially unchanged.
            </p>
          </div>
        </div>
      </div>

      <div className="max-w-7xl mx-auto mt-12 flex pb-56">
        <div className="w-7/12 p-2 text-neutral-100">
          <div className="bg-zinc-800 rounded-xl p-6 mb-8">
            <h1 className="font-bold font-oswald text-4xl">Select your seat</h1>
            <div className="flex justify-between text-xl mt-8">
              <p>Date time</p>
              <p>20.20.2022 20:20</p>
            </div>
            <div className="flex justify-between text-xl mt-2">
              <p>Selected seat</p>
              <p>47</p>
            </div>
            <div className="flex justify-between text-xl mt-2">
              <p>Regular price</p>
              <p>100zł</p>
            </div>
          </div>
          <div className="bg-orange-500 rounded-xl p-6 text-neutral-100">
            <h1 className="font-bold font-oswald text-4xl">Payment</h1>
            <div className="w-full mt-8">
              <select
                id="options"
                defaultValue=""
                className="block w-full px-4 py-2 text-zinc-900 bg-neutral-100 rounded-xl shadow-sm hover:shadow-lg transition duration-200"
              >
                <option value="" disabled>
                  Apply discount
                </option>
                <option value="option1">Option 1</option>
                <option value="option2">Option 2</option>
                <option value="option3">Option 3</option>
                <option value="option4">Option 4</option>
              </select>
            </div>

            <div className="flex justify-between text-xl mt-2">
              <p>Discount value</p>
              <p>13zł</p>
            </div>
            <div className="flex justify-between text-xl mt-2 font-bold">
              <p>Total price</p>
              <p>83zł</p>
            </div>

            <button className="w-full mt-8 flex justify-center items-center px-4 py-3 bg-neutral-100 rounded-3xl transition duration-200 ease-in-out hover:bg-neutral-300">
              <span className="material-icons text-orange-500">add</span>
              <p className="text-orange-500 font-bold text-md lg:text-lg">
                Buy Ticket
              </p>
            </button>
          </div>
        </div>
        <div className="w-full  p-2">
          <div className="bg-zinc-800 h-full rounded-xl p-6 text-neutral-100">
            <p>seats</p>
          </div>
        </div>
      </div>
    </div>
  );
}
