"use client";

import Navbar from "@/app/components/Navbar";
import { Room, Seat, Ticket } from "@/app/types/interfaces";
import { ChangeEvent, useEffect, useState } from "react";
import SeatComponent from "../components/Seat";

export default function Reservation() {
  const [reservation, setReservation] = useState<{
    title: string;
    fullDate: string;
    room: Room;
    screeningId: number;
    selectedSeats: Seat[];
    price: number;
  } | null>(null);
  const [discounts, setDiscounts] = useState<
    { discount_id: number; value: number; name: string }[]
  >([]);
  const [tickets, setTickets] = useState<Ticket[]>([]);

  useEffect(() => {
    const storedReservation = sessionStorage.getItem("reservation");
    if (storedReservation) {
      setReservation(JSON.parse(storedReservation));
    }
  }, []);

  useEffect(() => {
    async function fetchDiscountNames() {
      if (reservation?.room.roomId) {
        const res = await fetch(`http://localhost:8080/discounts`);
        const data = await res.json();

        setDiscounts(data);

        const newTickets: Ticket[] = [];
        reservation.selectedSeats.forEach((seat) => {
          newTickets.push({
            seatId: seat.seatId,
            seatNumber: seat.seatNumber,
            screeningId: reservation.screeningId,
            discountName: data[0].name,
            basePrice: reservation.price,
            discountValue: data[0].value,
          });
        });
        setTickets([...tickets, ...newTickets]);
      }
    }
    if (reservation) {
      fetchDiscountNames();
    }
  }, [reservation]);

  const handleConfirmation = async () => {
    const res = await fetch(`http://localhost:8080/tickets`, {
      method: "post",
      body: JSON.stringify(tickets),
      credentials: "include",
      headers: {
        "Content-Type": "application/json",
      },
    });
    const data = await res.text();
    console.log(data);
  };

  const handleDiscountChange = (
    index: number,
    event: ChangeEvent<HTMLSelectElement>
  ) => {
    const newDiscountName = event.target.value;
    const newDiscountValue = discounts.filter(
      (discount) => discount.name === event.target.value
    )[0].value;

    setTickets((prevTickets) => {
      const updatedTickets = [...prevTickets];
      updatedTickets[index].discountName = newDiscountName;
      updatedTickets[index].discountValue = newDiscountValue;
      return updatedTickets;
    });
  };

  if (!discounts) {
    return (
      <div className="bg-zinc-900 max-w-screen min-h-screen text-neutral-100 flex items-center justify-center">
        <p>Loading discounts...</p>
      </div>
    );
  }

  if (!reservation) {
    return (
      <div className="bg-zinc-900 max-w-screen min-h-screen text-neutral-100 flex items-center justify-center">
        <p>Loading reservation...</p>
      </div>
    );
  }

  return (
    <div className="bg-zinc-900 max-w-screen min-h-screen text-neutral-100">
      <Navbar />

      <div className="container max-w-6xl mx-auto flex justify-center items-center flex-col">
        {tickets.map((ticket, index) => (
          <div key={index} className="flex items-center justify-between w-3/4">
            <SeatComponent
              key={ticket.seatId}
              seatId={ticket.seatId}
              seatNumber={ticket.seatNumber}
              isTaken={false}
              isSelected={false}
              pickSeatAction={() => {}}
            />

            <p className="text-l font-bold mx-4">{reservation.title}</p>
            <p className="text-l mx-4">
              {reservation.fullDate.split("T")[0]}{" "}
              {reservation.fullDate
                .split("T")[1]
                .split(":")
                .slice(0, -1)
                .join(":")}
            </p>

            <select
              className="bg-zinc-900 text-neutral-100 h-auto items-center justify-center mx-4"
              name="discount"
              id={"discount" + ticket.seatId}
              value={ticket.discountName}
              onChange={(event) => handleDiscountChange(index, event)}
            >
              {discounts.map((discount, index) => (
                <option
                  className="bg-zinc-900 text-neutral-100"
                  key={ticket.seatId * discounts.length + index}
                  value={discount.name}
                >
                  {discount.name}
                </option>
              ))}
            </select>

            <span>
              {Math.round(ticket.basePrice * (1 - ticket.discountValue) * 100) /
                100}{" "}
              PLN
            </span>
          </div>
        ))}
        <div className="flex items-center justify-end w-3/4 border-t-2 mb-8">
          {tickets.reduce(
            (a, ticket) =>
              a +
              Math.round(ticket.basePrice * (1 - ticket.discountValue) * 100) /
                100,
            0
          )}{" "}
          PLN
        </div>
        <button
          onClick={handleConfirmation}
          disabled={false}
          className={`flex w-fit mb-10 justify-center items-center px-4 py-3 text-md ${
            true ? "bg-orange-500 hover:bg-orange-600" : "bg-neutral-700"
          } rounded-3xl transition duration-200 ease-in-out`}
        >
          <p className="text-zinc-900 font-bold text-md lg:text-lg">Confirm</p>
        </button>
      </div>
    </div>
  );
}
