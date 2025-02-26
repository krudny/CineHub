export function convertToHours(minutes: number) {
  const hours = Math.floor(minutes / 60);
  const minutes_left = minutes % 60;
  return (
    hours + ":" + (minutes_left < 10 ? "0" + minutes_left : minutes_left) + "h"
  );
}

export function formatDate(isoDate: string) {
  const date = new Date(isoDate);
  const options: Intl.DateTimeFormatOptions = {
    weekday: "long",
    day: "2-digit",
    month: "2-digit",
  };
  return date.toLocaleDateString("en-US", options);
}
