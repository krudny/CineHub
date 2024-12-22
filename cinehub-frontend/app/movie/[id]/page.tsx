
import Navbar from "@/app/components/Navbar";
import {MovieResponse} from "@/app/types/interfaces";
import MovieInfo from "@/app/components/MovieInfo";
import TicketSummary from "@/app/components/TicketSummary";
import SeatsGrid from "@/app/components/SeatsGrid";




async function getMovie(id: string) {
  const res = await fetch(`http://localhost:8080/movies/${id}`, { cache: "force-cache" });
  return res.json();
}

async function getDiscountsNames() {
  const res = await fetch(`http://localhost:8080/discounts/names`, { cache: "force-cache" })
  return res.json();
}


export default async function MoviePage({ params }: { params: { id: string } })  {
  const { id } = await params;
  const movie: MovieResponse = await getMovie(id);
  const discounts: string[] = await getDiscountsNames();




  return (
    <div className="bg-zinc-900 max-w-screen">
      <Navbar />

      <MovieInfo {...movie} />

      <div className="max-w-7xl mx-auto mt-12 flex pb-56">
        <TicketSummary discounts={discounts} />
        <SeatsGrid />
      </div>
    </div>
  );
}
