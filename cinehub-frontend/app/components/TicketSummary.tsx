"use client";

import {useEffect, useState} from "react";

interface DiscountsProps {
  discounts: string[];
}




export default function TicketSummary({discounts}: DiscountsProps) {
  const [selectedDiscount, setSelectedDiscount] = useState<string | null>(null);
  const [discountValue, setDiscountValue] = useState<number>(1);
  const [price, setPrice] = useState<number>(100);

  const handleSelect = (value: string) => {
    setSelectedDiscount(value);
  };

  // TODO
  useEffect(() => {
    if (selectedDiscount === "Standard") {
      setDiscountValue(0.15)
    }
    if (selectedDiscount === "Child") {
      setDiscountValue(0.45)
    }
  }, [selectedDiscount]);


  return (
      <div className="w-7/12  p-2 text-neutral-100 flex flex-col justify-between">
        <div className="bg-zinc-800 rounded-xl p-6 mb-8 h-full">
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
        <div className="bg-orange-500 rounded-xl p-6 text-neutral-100 h-full">
          <h1 className="font-bold font-oswald text-4xl">Payment</h1>
          <div className="w-full mt-8">
            <select
                id="options"
                defaultValue=""
                className="block w-full px-4 py-2 text-zinc-900 bg-neutral-100 rounded-xl shadow-sm hover:shadow-lg transition duration-200 active:border-none"
                onChange={(event) => handleSelect(event.target.value)}
            >
              <option value="" disabled>
                Apply discount
              </option>
              {discounts.map((discount: string) => {
                return (
                    <option key={discount} value={discount}>{discount}</option>
                )
              })}
            </select>
          </div>

          <div className="flex justify-between text-xl mt-2">
            <p>Discount value</p>
            <p>{Math.round(price * discountValue)} zł</p>
          </div>
          <div className="flex justify-between text-xl mt-2 font-bold">
            <p>Total price</p>
            <p>{Math.round(price * (1 - discountValue))} zł</p>
          </div>

          <button
              className="w-full mt-8 flex justify-center items-center px-4 py-3 bg-neutral-100 rounded-3xl transition duration-200 ease-in-out hover:bg-neutral-300">
            <span className="material-icons text-orange-500">add</span>
            <p className="text-orange-500 font-bold text-md lg:text-lg">
              Buy Ticket
            </p>
          </button>
        </div>
      </div>
  )
}